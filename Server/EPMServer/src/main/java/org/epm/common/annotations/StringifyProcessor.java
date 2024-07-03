package org.epm.common.annotations;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.ElementKind;
import javax.tools.Diagnostic;
import javax.annotation.processing.Filer;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

@SupportedAnnotationTypes("Stringify")
@SupportedSourceVersion(SourceVersion.RELEASE_21)
public class StringifyProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element element : roundEnv.getElementsAnnotatedWith(Stringify.class)) {
            if (element.getKind() == ElementKind.CLASS) {
                TypeElement typeElement = (TypeElement) element;
                generateToStringMethod(typeElement);
            }
        }
        return true;
    }

    private void generateToStringMethod(TypeElement typeElement) {
        String className = typeElement.getSimpleName().toString();
        String packageName = processingEnv.getElementUtils().getPackageOf(typeElement).getQualifiedName().toString();
        String qualifiedName = packageName + "." + className;

        // Check if the class already has a toString method
        boolean hasToStringMethod = typeElement.getEnclosedElements().stream()
                .anyMatch(e -> e.getSimpleName().toString().equals("toString") && e.getKind() == ElementKind.METHOD);

        if (hasToStringMethod) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "Class " + className + " already has a toString() method. Skipping code generation.");
            return;
        }

        // Generate the toString() method
        StringBuilder toStringMethod = new StringBuilder();
        toStringMethod.append("package ").append(packageName).append(";\n\n")
                .append("public class ").append(className).append(" {\n")
                .append("    @Override\n")
                .append("    public String toString() {\n")
                .append("        return \"").append(className).append(" {\" +\n");

        // Generate field list
        boolean isFirstField = true;
        for (Element enclosedElement : typeElement.getEnclosedElements()) {
            if (enclosedElement.getKind().isField()) {
                if (!isFirstField) {
                    toStringMethod.append("            \", \" +\n");
                }
                String fieldName = enclosedElement.getSimpleName().toString();
                toStringMethod.append("            \"").append(fieldName).append(" = \" + ")
                        .append("this.").append(fieldName).append(" +\n");
                isFirstField = false;
            }
        }

        toStringMethod.append("            \"}\";\n")
                .append("    }\n")
                .append("}\n");

        try {
            Filer filer = processingEnv.getFiler();
            JavaFileObject fileObject = filer.createSourceFile(qualifiedName);
            try (Writer writer = fileObject.openWriter()) {
                writer.write(toStringMethod.toString());
            }
        } catch (IOException e) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Failed to generate source file for " + qualifiedName);
        }
    }
}

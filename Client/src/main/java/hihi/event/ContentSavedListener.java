package hihi.event;

import hihi.content.material.Material;
import hihi.content.project.Project;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ContentSavedListener {

//    @EventListener
//    public <T> void handleContentSavedEvent(ContentSavedEvent<T> event) {
//        T content = event.getContent();
//        // Obsługa po zapisie treści, np. aktualizacja interfejsu użytkownika
//        System.out.println("Content saved: " + content.toString());
//
//        // Jeśli musisz podjąć różne działania w zależności od typu treści:
//        if (content instanceof Project) {
//            // Obsługa projektu
//        } else if (content instanceof Material) {
//            // Obsługa materiału
//        }
//        // Dodaj inne typy treści, jeśli potrzebne
//    }
}

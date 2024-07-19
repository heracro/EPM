package org.epm.material;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.epm.AbstractMainTestParameterProvider;
import org.epm.common.utils.FontColor;
import org.epm.material.enums.Unit;
import org.epm.material.model.MaterialDTO;
import org.epm.material.model.MaterialEntity;
import org.epm.material.model.MaterialMapper;
import org.epm.material.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.epm.common.utils.ConsoleStringUtils.fontColor;

@Slf4j
@Component
public class MaterialTestParameterProvider
        extends AbstractMainTestParameterProvider<MaterialEntity, MaterialDTO> {

    private final int OBJECT_COUNT_IN_VALID_STREAMS = 25 * RUN_COUNT;
    private final Random random = new Random();
    private final int ALL_ATTR_COUNT = 9;
    private final int CORRUPTIBLE_ATTR_COUNT = 7;
    private final int RUNS = ALL_ATTR_COUNT * CORRUPTIBLE_ATTR_COUNT * RUN_COUNT;
    
    private void info(String format, Object... args) {
        log.info(fontColor(FontColor.BRIGHT_YELLOW, format, args));
    }

    @Autowired
    private MaterialMapper mapper;

    @Autowired
    private MaterialRepository repository;

    @Override
    protected Integer provideUidOfExistingEntity() {
        info("provideUidOfExistingEntity()");
        MaterialEntity material = repository.findFirstByOrderByUidAsc().orElseThrow(
                () -> new IllegalStateException("Material database is empty!")
        );
        info("provideUidOfExistingEntity() --> getting ID from: {}", material);
        return material.getUid();
    }

    @Override
    protected Integer provideUidOfInvalidEntity() {
        info("provideUidOfInvalidEntity()");
        int uid;
        do {
            uid = new Random().nextInt(2000);
        } while (repository.findByUid(uid).isPresent());
        return uid;
    }

    @Override
    protected Integer provideUidOfUnconstrainedEntity() {
        info("provideUidOfUnconstrainedEntity()");
        return repository.save(provideValidEntity()).getUid();
    }

    @Override
    protected Stream<MaterialDTO> provideFewDTOsWhichAreValidEntity() {
        info("provideFewDTOsWhichAreValidEntity()");
        List<MaterialDTO> dtos = new ArrayList<>();
        for (int i = 0; i < OBJECT_COUNT_IN_VALID_STREAMS; ++i) {
            dtos.add(provideDTOWhichIsValidEntity());
        }
        return dtos.stream();
    }

    @Override
    protected Stream<MaterialDTO> provideFewDTOsWhichAreInvalidEntity() {
        info("provideFewDTOsWhichAreInvalidEntity()");
        List<MaterialDTO> dtos = new ArrayList<>();
        for (int i = 0; i < RUNS; ++i) {
            dtos.add(provideDTOWhichIsValidEntityWithSingleInvalidAttribute(i));
        }
        return dtos.stream();
    }

    @Override
    protected Stream<MaterialDTO> provideDTOsWithSingleValidAttribute() {
        info("provideDTOsWithSingleValidAttribute()");
        List<MaterialDTO> dtos = new ArrayList<>();
        for (int i = 0; i < RUNS; ++i) {
            dtos.add(provideEmptyDTOWithSingleValidAttribute(i));
        }
        return dtos.stream();
    }

    @Override
    protected Stream<MaterialDTO> provideDTOsWithSingleInvalidAttribute() {
        info("provideDTOsWithSingleInvalidAttribute()");
        List<MaterialDTO> dtos = new ArrayList<>();
        for (int i = 0; i < RUNS; ++i) {
            dtos.add(provideEmptyDTOWithSingleInvalidAttribute(i));
        }
        return dtos.stream();
    }

    protected MaterialEntity setSingleValidAttribute(@NonNull MaterialEntity material, int caseNumber) {
        info("setSingleValidAttribute(@NonNull MaterialEntity {}, final int {})", material, caseNumber);
        switch (caseNumber % ALL_ATTR_COUNT) {
            case 0 -> setRandomValidName(material);
            case 1 -> setRandomValidNorm(material);
            case 2 -> setRandomValidDatasheet(material);
            case 3 -> setRandomValidDimensions(material);
            case 4 -> setRandomValidDescription(material);
            case 5 -> setRandomValidWeight(material);
            case 6 -> setRandomValidTotalQty(material);
            case 7 -> setRandomValidFreeQty(material);
            case 8 -> setRandomValidUnit(material);
        }
        info(material.toString());
        return material;
    }

    protected MaterialDTO provideEmptyDTOWithSingleValidAttribute() {
        info("provideEmptyDTOWithSingleValidAttribute()");
        return mapper.toDto(setSingleValidAttribute(new MaterialEntity(), random.nextInt(ALL_ATTR_COUNT)));
    }
    protected MaterialDTO provideEmptyDTOWithSingleValidAttribute(int caseNumber) {
        info("provideEmptyDTOWithSingleValidAttribute({})", caseNumber);
        return mapper.toDto(setSingleValidAttribute(new MaterialEntity(), caseNumber));
    }

    public MaterialEntity provideValidEntity() {
        info("provideValidEntity()");
        MaterialEntity material = new MaterialEntity();
        for (int i = 0; i < ALL_ATTR_COUNT; i++) {
            material = setSingleValidAttribute(material, i);
        }
        info(material.toString());
        return material;
    }

    public MaterialDTO provideDTOWhichIsValidEntity() {
        info("provideDTOWhichIsValidEntity()");
        return mapper.toDto(provideValidEntity());
    }

    protected MaterialEntity setSingleInvalidAttribute(@NonNull MaterialEntity material, final int caseNumber) {
        info("setSingleInvalidAttribute(@NonNull MaterialEntity {}, final int {})", material, caseNumber);
        switch (caseNumber % CORRUPTIBLE_ATTR_COUNT) {
            case 0 -> setRandomInvalidName(material);
            case 1 -> setRandomInvalidNorm(material);
            case 2 -> setRandomInvalidDatasheet(material);
            case 3 -> setRandomInvalidDimensions(material);
            case 4 -> setRandomInvalidWeight(material);
            case 5 -> setRandomInvalidTotalQty(material);
            case 6 -> setRandomInvalidFreeQty(material);
        }
        info(material.toString());
        return material;
    }


    protected MaterialDTO provideEmptyDTOWithSingleInvalidAttribute() {
        info("provideEmptyDTOWithSingleInvalidAttribute()");
        return mapper.toDto(setSingleInvalidAttribute(new MaterialEntity(), random.nextInt(CORRUPTIBLE_ATTR_COUNT)));
    }
    protected MaterialDTO provideEmptyDTOWithSingleInvalidAttribute(int caseNumber) {
        info("provideEmptyDTOWithSingleInvalidAttribute({})", caseNumber);
        return mapper.toDto(setSingleInvalidAttribute(new MaterialEntity(), caseNumber));
    }

    protected MaterialEntity provideValidEntityWithSingleInvalidAttribute() {
        info("provideValidEntityWithSingleInvalidAttribute()");
        MaterialEntity material = provideValidEntity();
        setSingleInvalidAttribute(material, random.nextInt(ALL_ATTR_COUNT));
        info(material.toString());
        return material;
    }
    protected MaterialEntity provideValidEntityWithSingleInvalidAttribute(int caseNumber) {
        info("provideValidEntityWithSingleInvalidAttribute({})", caseNumber);
        MaterialEntity material = provideValidEntity();
        setSingleInvalidAttribute(material, caseNumber);
        info(material.toString());
        return material;
    }

    protected MaterialDTO provideDTOWhichIsValidEntityWithSingleInvalidAttribute() {
        info("provideDTOWhichIsValidEntityWithSingleInvalidAttribute()");
        return mapper.toDto(provideValidEntityWithSingleInvalidAttribute());
    }
    protected MaterialDTO provideDTOWhichIsValidEntityWithSingleInvalidAttribute(int caseNumber) {
        info("provideDTOWhichIsValidEntityWithSingleInvalidAttribute({})", caseNumber);
        return mapper.toDto(provideValidEntityWithSingleInvalidAttribute(caseNumber));
    }

    void setRandomValidName(MaterialEntity material) {
        info("setRandomValidName(MaterialEntity {})", material);
        material.setName("Material " + random.nextInt(2000));
        info(material.toString());
    }
    void setRandomInvalidName(MaterialEntity material) {
        info("setRandomInvalidName(MaterialEntity {})", material);
        material.setName("." + random.nextInt(100)); //too short
        info(material.toString());
    }
    void setRandomValidNorm(MaterialEntity material) {
        info("setRandomValidNorm(MaterialEntity {})", material);
        material.setNorm((random.nextBoolean() ? "DIN" : "ISO") + (random.nextInt(1500) + 500));
        info(material.toString());
    }
    void setRandomInvalidNorm(MaterialEntity material) {
        info("setRandomInvalidNorm(MaterialEntity {})", material);
        material.setNorm("|" + random.nextInt(100));
        info(material.toString());
    }
    void setRandomValidDatasheet(MaterialEntity material) {
        info("setRandomValidDatasheet(MaterialEntity {})", material);
        material.setDatasheet("Datasheet " + random.nextInt(2000) + ".pdf");
        info(material.toString());
    }
    void setRandomInvalidDatasheet(MaterialEntity material) {
        info("setRandomInvalidDatasheet(MaterialEntity {})", material);
        material.setDatasheet(String.valueOf(random.nextInt(1000)));
        info(material.toString());
    }
    void setRandomValidDimensions(MaterialEntity material) {
        info("setRandomValidDimensions(MaterialEntity {})", material);
        material.setDimensions(random.nextInt(100) + "x" + random.nextInt(100));
        info(material.toString());
    }
    void setRandomInvalidDimensions(MaterialEntity material) {
        info("setRandomInvalidDimensions(MaterialEntity {})", material);
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789*/[](),|'\"}{_-+=!@#$%^&*()~`\\";
        material.setDimensions(String.valueOf(characters.charAt(random.nextInt(characters.length()))));
        info(material.toString());
    }
    void setRandomValidDescription(MaterialEntity material) {
        info("setRandomValidDescription(MaterialEntity {})", material);
        for (int i = 0; i < random.nextInt(3, 20); ++i) {
            material.setDescription(material.getDescription() + "Some description");
        }
        info(material.toString());
    }
    void setRandomValidWeight(MaterialEntity material) {
        info("setRandomValidWeight(MaterialEntity {})", material);
        material.setWeight(random.nextFloat(200.0f));
        info(material.toString());
    }
    void setRandomInvalidWeight(MaterialEntity material) {
        info("setRandomInvalidWeight(MaterialEntity {})", material);
        material.setWeight(random.nextFloat(100f) - 100);
        info(material.toString());
    }
    void setRandomValidTotalQty(MaterialEntity material) {
        info("setRandomValidTotalQty(MaterialEntity {})", material);
        material.setTotalQty(random.nextBoolean() ? random.nextInt(5) : 0);
        info(material.toString());
    }
    void setRandomInvalidTotalQty(MaterialEntity material) {
        info("setRandomInvalidTotalQty(MaterialEntity {})", material);
        material.setTotalQty(random.nextBoolean() ? random.nextInt(100) - 100 : null);
        info(material.toString());
    }
    void setRandomValidFreeQty(MaterialEntity material) {
        info("setRandomValidFreeQty(MaterialEntity {})", material);
        material.setFreeQty(Math.min(
                random.nextBoolean() ? random.nextInt(100) : 0,
                material.getTotalQty() == null ? 0 : material.getTotalQty())
        );
        info(material.toString());
    }
    void setRandomInvalidFreeQty(MaterialEntity material) {
        info("setRandomInvalidFreeQty(MaterialEntity {})", material);
        if (material.getTotalQty() != null) {
            material.setFreeQty(random.nextBoolean() ?
                    random.nextInt(10) + material.getTotalQty() + 1 :
                    random.nextInt(5) - 5);
        } else {
            material.setFreeQty(random.nextInt(5) - 5);
        }
        info(material.toString());
    }
    void setRandomValidUnit(MaterialEntity material) {
        info("setRandomValidUnit(MaterialEntity {})", material);
        material.setUnit(Unit.randomUnit());
        info(material.toString());
    }

    void setRandomAction(MaterialDTO dto) {
        info("setRandomAction(MaterialDTO {})", dto);
        dto.setAction("Action " + random.nextInt(100));
        info(dto.toString());
    }
}

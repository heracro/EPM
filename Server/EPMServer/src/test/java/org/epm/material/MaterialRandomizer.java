package org.epm.material;

import org.epm.material.enums.Unit;
import org.epm.material.model.MaterialEntity;

import java.util.Random;

public class MaterialRandomizer {

    public static MaterialEntity randomInstance() {
        MaterialEntity m = new MaterialEntity();
        Random random = new Random();
        m.setName("Material " + random.nextInt(2000));
        m.setNorm((random.nextBoolean() ? "DIN" : "ISO") + (random.nextInt(1500) + 500));
        m.setDimensions("" + random.nextInt(200) + "x" + random.nextInt(160) + "x" + random.nextInt(40));
        m.setDescription("Some description");
        m.setWeight(random.nextInt(30000) / 100f);
        m.setTotalQty(random.nextBoolean() || random.nextBoolean() ? random.nextInt(60) : 0);
        m.setFreeQty((random.nextBoolean() || random.nextBoolean()) && m.getTotalQty() > 0 ? random.nextInt(m.getTotalQty()) : 0);
        m.setUnit(Unit.randomUnit());
        return m;
    }
}

package org.epm.bom;

import org.epm.bom.enums.BomStatus;
import org.epm.project.enums.ProjectStatus;

import java.util.Random;

public class BomRandomizer {

    public static BomStatus randomBomStatus(ProjectStatus status) {
        Random random = new Random();
        if (status == ProjectStatus.ONGOING || status == ProjectStatus.COMPLETED) {
            return BomStatus.TAKEN;
        } else {
            return BomStatus.values()[random.nextInt(BomStatus.values().length - 1)];
        }
    }

}

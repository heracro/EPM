package hihi.caller;

import hihi.dto.Bom;
import hihi.dto.Delivery;
import hihi.dto.Material;
import hihi.dto.Project;

public class WarehouseCallerService {

    private final String apiUrl = "http://kartofel:8082/warehouse/";

//==================== PROJECT ===========================
    Project findProject(Integer projectId) {
        return new Project();
    }

    Project createProject(Project project) {
        return project;
    }

    Project updateProject(Integer projectId, Project update) {
        return update;
    }

    void deleteProject(Integer projectId) {

    }

//==================== MATERIAL ==========================

    Material getMaterial(Integer materialId) {
        return new Material();
    }
//==================== BOM ===============================

    Bom getBom(Integer projectId, Integer materialId) {
        return new Bom();
    }
//==================== DELIVERY ==========================

    Delivery getDelivery(Integer deliveryId) {
        return new Delivery();
    }

//==================== REPORT ============================


}

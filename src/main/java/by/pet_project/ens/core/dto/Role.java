package by.pet_project.ens.core.dto;

public enum Role {
    ADMIN(1), USER(2);
    private final int role_id;

    Role(int role_id) {
        this.role_id = role_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public static Role fromRoleId(int role_id) {
        for (Role role : Role.values()) {
            if (role.getRole_id() == role_id) {
                return role;
            }
        }
        return null;
    }
}

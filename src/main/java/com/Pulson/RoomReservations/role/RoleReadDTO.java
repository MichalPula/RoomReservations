package com.Pulson.RoomReservations.role;

public class RoleReadDTO {

    private String formattedRoleType;

    public RoleReadDTO(String formattedRoleType) {
        this.formattedRoleType = formattedRoleType;
    }

    public RoleReadDTO() {
    }

    public String getFormattedRoleType() {
        return formattedRoleType;
    }

    public void setFormattedRoleType(String formattedRoleType) {
        this.formattedRoleType = formattedRoleType;
    }
}

package zack.security;

public enum ApplicationUserPermission {
    // STUDENT_READ.name() => "STUDENT_READ"    /   STUDENT_READ.getPermission() => "student:read"

    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
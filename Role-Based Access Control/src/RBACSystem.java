
import java.util.*;

public class RBACSystem {

    // Store permissions for each role
    static Map<String, Set<String>> rolePermissions = new HashMap<>();

    // Store the role assigned to each user
    static Map<String, String> userRoles = new HashMap<>();

    // Assign permissions to roles
    public static void setupRoles() {

        rolePermissions.put("Admin",
                new HashSet<>(Arrays.asList(
                        "Create", "View", "Update", "Delete"
                )));

        rolePermissions.put("Manager",
                new HashSet<>(Arrays.asList(
                        "View", "Update"
                )));

        rolePermissions.put("Customer",
                new HashSet<>(Arrays.asList(
                        "View"
                )));
    }

    // Assign a role to a user
    public static void assignRole(String username, String role) {

        if (rolePermissions.containsKey(role)) {
            userRoles.put(username, role);
            System.out.println(username + " assigned to " + role);
        } else {
            System.out.println("Invalid role: " + role);
        }
    }

    // Check whether a user has permission
    public static boolean hasPermission(
            String username, String permission) {

        String role = userRoles.get(username);

        if (role == null) {
            return false;
        }

        return rolePermissions.get(role).contains(permission);
    }

    // Display the result of an access request
    public static void checkAccess(
            String username, String permission) {

        if (hasPermission(username, permission)) {
            System.out.println(username + " is allowed to "
                    + permission + " accounts.");
        } else {
            System.out.println(username + " is NOT allowed to "
                    + permission + " accounts.");
        }
    }

    public static void main(String[] args) {

        setupRoles();

        // Assign users to roles
        assignRole("Mo'men", "Admin");
        assignRole("Ahmad", "Manager");
        assignRole("Sara", "Customer");

        System.out.println("\n--- Access Control Tests ---");

        // Admin tests
        checkAccess("Mo'men", "Create");
        checkAccess("Mo'men", "Delete");

        // Manager tests
        checkAccess("Ahmad", "View");
        checkAccess("Ahmad", "Update");
        checkAccess("Ahmad", "Delete");

        // Customer tests
        checkAccess("Sara", "View");
        checkAccess("Sara", "Update");
    }
}

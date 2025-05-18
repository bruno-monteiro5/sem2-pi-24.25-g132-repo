package pt.ipp.isep.dei.controller;

import pt.ipp.isep.dei.repository.AuthenticationRepository;
import pt.ipp.isep.dei.repository.Repositories;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.util.List;

/**
 * Controller responsible for managing the user authentication process,
 * including login and logout operations, as well as handling user sessions.
 * <p>
 * This class interacts with the {@link AuthenticationRepository} to perform
 * authentication-related operations.
 */
public class AuthenticationController {

    public static final String ROLE_EDITOR = "EDITOR";
    public static final String ROLE_PLAYER = "PLAYER";

    private final AuthenticationRepository authenticationRepository;

    /**
     * Creates an instance of {@code AuthenticationController}, initializing
     * the authentication repository from the shared repository instance.
     */
    public AuthenticationController() {
        this.authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    /**
     * Performs user login by verifying the provided email and password.
     *
     * @param email The email address of the user attempting to log in.
     * @param pwd   The password of the user.
     * @return {@code true} if login is successful, {@code false} otherwise.
     *         Returns {@code false} for invalid credentials or if an exception occurs.
     */
    public boolean doLogin(String email, String pwd) {
        try {
            return authenticationRepository.doLogin(email, pwd);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

    /**
     * Retrieves a list of roles assigned to the currently logged-in user.
     * <p>
     * This method checks if there is an active user session. If a user is logged in,
     * it returns the roles associated with the current user. Otherwise, it returns {@code null}.
     *
     * @return A {@link List} of {@link UserRoleDTO} objects representing the user's roles,
     *         or {@code null} if no user is currently logged in.
     */
    public List<UserRoleDTO> getUserRoles() {
        if (authenticationRepository.getCurrentUserSession().isLoggedIn()) {
            return authenticationRepository.getCurrentUserSession().getUserRoles();
        }
        return null;
    }

    /**
     * Logs out the currently logged-in user, ending the authentication session.
     */
    public void doLogout() {
        authenticationRepository.doLogout();
    }
}
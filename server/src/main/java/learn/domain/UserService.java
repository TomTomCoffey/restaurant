package learn.domain;

import learn.data.UserRepository;
import learn.models.User;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
 //   private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
      //  this.encoder = encoder;
        this.encoder = encoder;
    }

    public List<User> findAll(){
        return repository.findAll();

    }
    public User findByUserId(int id){
        return repository.findById(id);
    }
    public User findByUsername(String userName){
        return repository.findByUserName(userName);
    }
    public Result<User> add(User user){

        Result<User> result = validate(user);

        if(!result.isSuccess()){
            return result;
        }

        if(user.getUserId() != 0){
            result.addMessage("User cannot have a preset ID", ResultType.INVALID);
            return result;
        }
        user.setHashedPassword(encoder.encode(user.getHashedPassword()));
        User expected = repository.add(user);

        if(expected == null){
            result.addMessage("User failed to be added to database", ResultType.NOT_FOUND);
            return result;
        }

        result.setPayload(user);
        return result;

    }
    public Result<User> update(User user){

        Result<User> result = validate(user);

        if(!result.isSuccess()){
            return result;
        }
        User copy = repository.findById(user.getUserId());

        if(copy == null){
            result.addMessage("User is not found in database", ResultType.NOT_FOUND);
            return result;
        }
        boolean expected = repository.update(user);
        if(!expected){
            result.addMessage("An error occured in database when updating", ResultType.NOT_FOUND);
            return result;
        }
        result.setPayload(user);
        return result;

    }
    public boolean deleteById(int id){
        return repository.deleteById(id);
    }
    private Result<User> validate(User user){
        Result<User> result = new Result<>();

        if(user == null){
            result.addMessage("User cannot be null", ResultType.INVALID);
            return result;
        }
        if(user.getFirstName() == null || user.getFirstName().isEmpty() || user.getFirstName().isBlank()){
            result.addMessage("User must have first name", ResultType.INVALID);
        }
        if(user.getLastName() == null || user.getLastName().isEmpty() || user.getLastName().isBlank()){
            result.addMessage("User must have last name", ResultType.INVALID);
        }
        if(user.getUserName() == null || user.getUserName().isEmpty() || user.getUserName().isBlank()){
            result.addMessage("User must have user name", ResultType.INVALID);
        }
        if(user.getEmail() == null ||
                user.getEmail().isEmpty() ||
                user.getEmail().isBlank() ||
                !user.getEmail().endsWith(".com")){
            result.addMessage("User must have valid `.com`  email", ResultType.INVALID);
        }

        if (user.getHashedPassword() == null || !isValidPassword(user.getHashedPassword())) {
            result.addMessage("Password must be at least 8 characters long and contain one capital letter, one lowercase letter, one number, and one symbol", ResultType.INVALID);
        }

        ///check if username or emails are taken

        User checker = repository.findByUserName(user.getUserName());

        if(checker != null && checker.getUserId() != user.getUserId()){
            result.addMessage("Username is taken", ResultType.INVALID);
        }
        checker = repository.findAll().stream()
                .filter(u -> u.getEmail().equals(user.getEmail()) && u.getUserId() != user.getUserId())
                .findFirst().orElse(null);

        if(checker != null){
            result.addMessage("Email is taken", ResultType.INVALID);
        }

        return result;
    }

    private boolean isValidPassword(String hashedPassword) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(hashedPassword).matches();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DisabledException {
        User user = repository.findByUserName(username);

        if (user == null){
            throw new UsernameNotFoundException(username + " not found");
        }
        else if(user.isBanned()){
            throw new DisabledException(username + " is banned");
        }

        List<GrantedAuthority> authorities = user.getRoles().stream().map(roleName -> new SimpleGrantedAuthority("ROLE_" + roleName))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getHashedPassword(), authorities);
    }
}

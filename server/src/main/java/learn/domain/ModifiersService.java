package learn.domain;

import learn.data.ModifiersRepository;
import learn.models.Modifiers;
import org.springframework.stereotype.Service;

import java.lang.reflect.Modifier;
import java.util.List;

@Service
public class ModifiersService {

    private final ModifiersRepository repository;

    public ModifiersService(ModifiersRepository repository) {
        this.repository = repository;
    }

    public List<Modifiers> findAll(){
        return repository.findAll();
    }

    public List<Modifiers> findByItemId(int itemId){
        return repository.findByItemId(itemId);
    }

    public Result<Modifiers> add(Modifiers modifiers){
        Result<Modifiers> result = validate(modifiers);

        if(!result.isSuccess()){
            return result;
        }
        Modifiers modifiers1 = repository.add(modifiers);

        if(modifiers1 == null){
            result.addMessage("An Error occured in the repository", ResultType.INVALID);
            return result;
        }
        result.setPayload(modifiers1);
        return result;


    }
    public Result<Modifiers> update(Modifiers modifiers){
        Result<Modifiers> result = validate(modifiers);

        if(!result.isSuccess()){
            return result;
        }
        Modifiers m = repository.findAll().stream()
                .filter(mod -> mod.getModifier_id() == modifiers.getModifier_id())
                .findFirst()
                .orElse(null);

        if(m == null){
            result.addMessage("Cannot find item in database to update", ResultType.NOT_FOUND);
            return result;
        }
        boolean expected = repository.update(modifiers);
        if(!expected){
            result.addMessage("An error occured in the repository", ResultType.NOT_FOUND);
            return result;
        }
        result.setPayload(modifiers);
        return result;
    }

    public boolean deleteById(int id){
        return repository.deleteById(id);
    }

    private Result<Modifiers> validate(Modifiers modifiers){
        Result<Modifiers> result = new Result<>();

        if(modifiers.getName() == null || modifiers.getName().isEmpty() || modifiers.getName().isBlank()){
            result.addMessage("Modifiers must have a valid name", ResultType.INVALID);

        }
        if(modifiers.getPrice().doubleValue() < 0){
            result.addMessage("Modifiers cannot have negative price", ResultType.INVALID);

        }

        return result;
    }
}

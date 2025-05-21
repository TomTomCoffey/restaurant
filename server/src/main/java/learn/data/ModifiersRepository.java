package learn.data;

import learn.models.Modifiers;
import learn.models.ModifiersCategory;

import java.util.List;

public interface ModifiersRepository {

    public List<Modifiers> findAll();

    public List<Modifiers> findByItemId(int itemId);

    public Modifiers add(Modifiers modifiers);

    public boolean update(Modifiers modifiers);

    public boolean deleteById(int modifiersId);

    public ModifiersCategory getModifierCategory(int id);

    public List<ModifiersCategory> getAllModCat();
}

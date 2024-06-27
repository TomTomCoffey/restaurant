package learn.data;

import learn.models.Modifiers;

import java.util.List;

public interface ModifiersRepository {

    public List<Modifiers> findAll();

    public List<Modifiers> findByItemId(int itemId);

    public Modifiers add(Modifiers modifiers);

    public boolean update(Modifiers modifiers);

    public boolean deleteById(int modifiersId);
}

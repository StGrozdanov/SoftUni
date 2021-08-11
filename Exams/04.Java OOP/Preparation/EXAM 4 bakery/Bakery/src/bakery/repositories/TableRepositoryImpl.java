package bakery.repositories;

import bakery.entities.tables.interfaces.Table;

public class TableRepositoryImpl extends Repository<Table>
        implements bakery.repositories.interfaces.TableRepository<Table> {

    @Override
    public Table getByNumber(int number) {
        return super.getAll().stream().filter(table -> table.getTableNumber() == number).
                findFirst().orElse(null);
    }
}

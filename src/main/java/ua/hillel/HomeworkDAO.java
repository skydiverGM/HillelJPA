package ua.hillel;


public class HomeworkDAO extends AbstractGenericDAO<Homework, Long> {

    @Override
    protected Class<Homework> getEntityClass() {
        return Homework.class;
    }
}

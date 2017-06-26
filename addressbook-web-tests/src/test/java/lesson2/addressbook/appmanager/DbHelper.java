package lesson2.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import lesson2.addressbook.model.ContactData;
import lesson2.addressbook.model.Contacts;
import lesson2.addressbook.model.GroupData;
import lesson2.addressbook.model.Groups;
import java.util.List;

public class DbHelper {
    private final SessionFactory sessionFactory;

    public DbHelper() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'" ).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts (result);
    }

    public GroupData groupById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        GroupData group = (GroupData) session.createQuery( "from GroupData where group_id = '" + id + "'").getSingleResult();
        session.getTransaction().commit();
        session.close();
        return group;
    }

    public ContactData contactById(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ContactData contact = (ContactData) session.createQuery( "from ContactData where id = '" + id + "'").getSingleResult();
        session.getTransaction().commit();
        session.close();
        return contact;
    }
}

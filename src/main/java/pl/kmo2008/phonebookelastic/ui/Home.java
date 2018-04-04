package pl.kmo2008.phonebookelastic.ui;


import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.kmo2008.phonebookelastic.model.Person;
import pl.kmo2008.phonebookelastic.service.PersonService;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.StreamSupport;


@SpringUI()
public class Home extends UI {

    @Autowired
    PersonService personService;

    Grid grid = new Grid<>(Person.class);

    private Person lastSelect;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        Button save = new Button("Save");
        Button saveNew = new Button("Save");
        Button add = new Button("Add", VaadinIcons.PLUS);
        HorizontalLayout general = new HorizontalLayout();
        final boolean[] editable = {false};
        Button edit = new Button("Edit");
        Button delete = new Button("Del");
        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponents(save, edit, delete);
        final Window editWindow = new Window("Details");
        final Window addWindow = new Window("Add");
        editWindow.setWidth(300.0f, Unit.PIXELS);
        addWindow.setWidth(300.0f, Unit.PIXELS);
        final FormLayout windowContent = new FormLayout();
        final FormLayout windowAddContent = new FormLayout();
        windowContent.setMargin(true);
        editWindow.setContent(windowContent);
        editWindow.center();
        editWindow.setResizable(false);
        editWindow.setDraggable(false);
        addWindow.setContent(windowAddContent);
        addWindow.center();
        addWindow.setResizable(false);
        addWindow.setDraggable(false);
        TextField nameField = new TextField("Name:");
        TextField phoneField = new TextField("Phone:");
        TextField nameNewField = new TextField("Name:");
        TextField phoneNewField = new TextField("Phone:");
        windowContent.addComponents(nameField, phoneField, buttons);
        windowAddContent.addComponents(nameNewField, phoneNewField, saveNew);
        grid.removeColumn("id");
        grid.setColumnOrder("name", "phone");
        gridReload();


        edit.addClickListener(clickEvent -> {
            if (!editable[0]) {
                nameField.setEnabled(true);
                phoneField.setEnabled(true);

            }
            else
            {
                nameField.setEnabled(false);
                phoneField.setEnabled(false);
            }
            editable[0] = !editable[0];
        });


        grid.addItemClickListener(itemClick ->
        {
            Person selectedPerson = (Person)  itemClick.getItem();
            lastSelect = selectedPerson;
            nameField.setValue(selectedPerson.getName());
            nameField.setEnabled(false);
            phoneField.setValue(selectedPerson.getPhone());
            phoneField.setEnabled(false);
            if (selectedPerson != null) addWindow(editWindow);

        });

        delete.addClickListener(clickEvent -> {
            Person selectedPerson = lastSelect;
            personService.delete(selectedPerson);
            editWindow.close();
            gridReload();
        });

        save.addClickListener(clickEvent -> {
            Person selectedPerson = lastSelect;
            personService.delete(selectedPerson);
            selectedPerson = new Person(nameField.getValue(), phoneField.getValue());
            personService.save(selectedPerson);
            editWindow.close();
            gridReload();
        });
        saveNew.addClickListener(clickEvent -> {
            Person newPerson = new Person(nameNewField.getValue(), phoneNewField.getValue());
            personService.save(newPerson);
            gridReload();
            addWindow.close();
        });

        add.addClickListener(clickEvent -> {
            nameNewField.setValue("");
            nameNewField.setEnabled(true);
            phoneNewField.setValue("");
            phoneNewField.setEnabled(true);
            addWindow(addWindow);
        });
        setSizeFull();
        general.addComponents(grid,add);
        setContent(general);

    }


    public void gridReload()
    {
        grid.setItems(StreamSupport.stream(personService.findAll().spliterator(), true)
                .sorted(Comparator.comparing(Person::getName)));
    }
}

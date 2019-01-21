package ru.job4j.array;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * StoreXML
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 18.01.2019
 */
public class StoreXML {
    /**
     * Target location.
     */
    private final File target;

    /**
     * Constructor.
     * @param target Target location.
     */
    public StoreXML(File target) {
        this.target = target;
    }

    /**
     * Get list entrys and generate file in location.
     * @param list list of Entrys.
     */
    public void save(List<Entry> list) {
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            List<Field> fields = new ArrayList<>(100);
            for (Entry aList : list) {
                fields.add(new Field(aList.getValue()));
            }
            jaxbMarshaller.marshal(new Entries(fields), this.target);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    @SuppressWarnings("unused")
    @XmlRootElement
    public static class Entries {
        private List<Field> entry;

        public Entries() {
        }

        public Entries(List<Field> entry) {
            this.entry = entry;
        }

        public List<Field> getEntry() {
            return this.entry;
        }
        @XmlElement
        public void setEntry(List<Field> entry) {
            this.entry = entry;
        }
    }

    @SuppressWarnings("unused")
    @XmlRootElement
    public static class Field {
        private int field;

        public Field() {
        }

        public Field(int field) {
            this.field = field;
        }

        public int getField() {
            return this.field;
        }

        @XmlElement
        public void setField(int field) {
            this.field = field;
        }
    }
}

package com.github.iceant.application.meta.core;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@ToString(callSuper = true)
public class DataShape extends NamedObject<DataShape>{

    String dataTypeName;
    Integer ordinal = 0;
    FieldShapeCollection fieldShapes = new FieldShapeCollection();
    AspectCollection aspects = new AspectCollection();

    public DataShape addFieldShape(FieldShape fieldShape){
        fieldShapes.put(fieldShape.getName(), fieldShape);
        fieldShape.setOrdinal(fieldShapes.size());
        return this;
    }

    public Boolean containsFieldShape(String fieldName){
        return fieldShapes.containsKey(fieldName);
    }

    public DataShape removeFieldShape(String fieldName){
        fieldShapes.remove(fieldName);
        return this;
    }

    public List<FieldShape> getFieldShapes(){
        List<FieldShape> fieldShapeList = new ArrayList<>(fieldShapes.values());
        fieldShapeList.sort(new Comparator<FieldShape>() {
            @Override
            public int compare(FieldShape o1, FieldShape o2) {
                return o1.getOrdinal().compareTo(o2.getOrdinal());
            }
        });
        return fieldShapeList;
    }

    public List<FieldShape> getFieldShapes(Comparator<FieldShape> fieldShapeComparator){
        List<FieldShape> fieldShapeList = new ArrayList<>(fieldShapes.values());
        fieldShapeList.sort(fieldShapeComparator);
        return fieldShapeList;
    }

    public FieldShape getFieldShape(String name) {
        return fieldShapes.get(name);
    }

    public DataShape setStringAspect(String name, String value){
        aspects.setString(name, value);
        return this;
    }

    public DataShape setIntegerAspect(String name, Integer value) {
        aspects.setInteger(name, value);
        return this;
    }

    public DataShape setBooleanAspect(String name, Boolean value) {
        aspects.setBooelan(name, value);
        return this;
    }
}

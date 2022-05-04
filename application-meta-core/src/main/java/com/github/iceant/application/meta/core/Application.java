package com.github.iceant.application.meta.core;

import com.github.iceant.application.meta.core.jackson.DataTypeCollectionDeserializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
@Accessors(chain = true)
public class Application extends NamedObject<Application>{

    DataTypeCollection dataTypes = new DataTypeCollection();

    DataShapeCollection dataShapes = new DataShapeCollection();

    public Application setDataTypes(DataTypeCollection dataTypes){
        this.dataTypes = dataTypes;
        return this;
    }

    public Application addDataType(DataType dataType){
        dataTypes.put(dataType.getName(), dataType);
        return this;
    }

    public Application addDataShape(DataShape dataShape){
        dataShapes.put(dataShape.getName(), dataShape);
        dataShape.setOrdinal(dataShapes.size());
        return this;
    }

    public DataType getDataType(String name){
        return dataTypes.get(name);
    }

    public List<DataType> getDataTypes(){
        List<DataType> dataTypeList = new ArrayList<>(dataTypes.values());
        dataTypeList.sort(new Comparator<DataType>() {
            @Override
            public int compare(DataType o1, DataType o2) {
                return o1.getOrdinal().compareTo(o2.getOrdinal());
            }
        });
        return dataTypeList;
    }

    public List<DataShape> getDataShapes(){
        List<DataShape> dataShapeList = new ArrayList<>(dataShapes.values());
        dataShapeList.sort(new Comparator<DataShape>() {
            @Override
            public int compare(DataShape o1, DataShape o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return dataShapeList;
    }

    public Application removeDataType(String name){
        dataTypes.remove(name);
        return this;
    }

    public Application removeDataShape(String name){
        dataShapes.remove(name);
        return this;
    }

    public DataShape getDataShape(String name) {
        return dataShapes.get(name);
    }
}

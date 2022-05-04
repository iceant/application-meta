package com.github.iceant.application.meta.core.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.iceant.application.meta.core.DataShape;
import com.github.iceant.application.meta.core.DataShapeCollection;

import java.io.IOException;

public class DataShapeCollectionDeserializer extends StdDeserializer<DataShapeCollection> {
    public static final DataShapeCollectionDeserializer INSTANCE= new DataShapeCollectionDeserializer(DataShapeCollection.class);

    protected DataShapeCollectionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public DataShapeCollection deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        DataShapeCollection dataShapeCollection = new DataShapeCollection();
        JsonNode node = p.readValueAsTree();
        ObjectCodec objectCodec = p.getCodec();
        if(node.isArray()){
            for(int i=0; i<node.size(); i++){
                JsonNode itemNode = node.get(i);
                DataShape dataShape = objectCodec.treeToValue(itemNode, DataShape.class);
                dataShapeCollection.put(dataShape.getName(), dataShape);
            }
        }
        return dataShapeCollection;
    }
}

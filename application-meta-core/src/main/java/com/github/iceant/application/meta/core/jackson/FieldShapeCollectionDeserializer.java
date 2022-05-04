package com.github.iceant.application.meta.core.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.iceant.application.meta.core.FieldShape;
import com.github.iceant.application.meta.core.FieldShapeCollection;

import java.io.IOException;

public class FieldShapeCollectionDeserializer extends StdDeserializer<FieldShapeCollection> {

    public static final FieldShapeCollectionDeserializer INSTANCE = new FieldShapeCollectionDeserializer(FieldShapeCollection.class);

    protected FieldShapeCollectionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public FieldShapeCollection deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        FieldShapeCollection fieldShapeCollection = new FieldShapeCollection();
        JsonNode node = p.readValueAsTree();
        ObjectCodec objectCodec = p.getCodec();
        if(node.isArray()){
            for(int i=0; i<node.size(); i++){
                JsonNode itemNode = node.get(i);
                FieldShape fieldShape = objectCodec.treeToValue(itemNode, FieldShape.class);
                fieldShapeCollection.put(fieldShape.getName(), fieldShape);
            }
        }
        return fieldShapeCollection;
    }
}

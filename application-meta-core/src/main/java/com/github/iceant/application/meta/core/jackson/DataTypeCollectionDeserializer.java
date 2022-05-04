package com.github.iceant.application.meta.core.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.iceant.application.meta.core.DataType;
import com.github.iceant.application.meta.core.DataTypeCollection;

import java.io.IOException;

public class DataTypeCollectionDeserializer extends StdDeserializer<DataTypeCollection> {

    public static final DataTypeCollectionDeserializer INSTANCE= new DataTypeCollectionDeserializer(DataTypeCollection.class);

    protected DataTypeCollectionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public DataTypeCollection deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        DataTypeCollection dataTypeCollection = new DataTypeCollection();
        JsonNode node = jsonParser.readValueAsTree();
        ObjectCodec objectCodec = jsonParser.getCodec();
        if(node.isArray()){
            for(int i=0; i<node.size(); i++){
                JsonNode itemNode = node.get(i);
                DataType dataType = objectCodec.readValue(itemNode.traverse(objectCodec), DataType.class);
                dataTypeCollection.put(dataType.getName(), dataType);
            }
        }
        return dataTypeCollection;
    }
}

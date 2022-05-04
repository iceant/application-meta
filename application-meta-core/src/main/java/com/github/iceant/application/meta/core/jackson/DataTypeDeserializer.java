package com.github.iceant.application.meta.core.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.iceant.application.meta.core.DataType;
import com.github.iceant.application.meta.core.PrimitiveDataType;

import java.io.IOException;

public class DataTypeDeserializer extends StdDeserializer<DataType> {

    public static final DataTypeDeserializer INSTANCE = new DataTypeDeserializer();

    public DataTypeDeserializer(){
        this(DataType.class);
    }
    protected DataTypeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public DataType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        DataType dataType = new DataType();
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode node = objectCodec.readTree(jsonParser);
        if(node.has("name")){
            dataType.setName(node.get("name").textValue());
        }
        if(node.has("type")){
            dataType.setValueType(PrimitiveDataType.valueOf(node.get("type").textValue()));
        }
        if(node.has("length")){
            dataType.setLength(node.get("length").longValue());
        }
        if(node.has("ordinal")){
            dataType.setOrdinal(node.get("ordinal").intValue());
        }
        return dataType;
    }
}

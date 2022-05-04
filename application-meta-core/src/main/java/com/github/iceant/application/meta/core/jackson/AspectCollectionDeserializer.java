package com.github.iceant.application.meta.core.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.iceant.application.meta.core.AspectCollection;
import com.github.iceant.application.meta.core.ITypeValue;

import java.io.IOException;
import java.util.Iterator;

public class AspectCollectionDeserializer extends StdDeserializer<AspectCollection> {
    public static final AspectCollectionDeserializer INSTANCE = new AspectCollectionDeserializer(AspectCollection.class);

    protected AspectCollectionDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public AspectCollection deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        AspectCollection aspectCollection = new AspectCollection();
        JsonNode node = p.readValueAsTree();
        ObjectCodec objectCodec = p.getCodec();
        for(Iterator<String> iter = node.fieldNames(); iter.hasNext();){
            String name = iter.next();
            JsonNode itemNode = node.get(name);
            ITypeValue typeValue = objectCodec.treeToValue(itemNode, ITypeValue.class);
            aspectCollection.put(name, typeValue);
        }
        return aspectCollection;
    }
}

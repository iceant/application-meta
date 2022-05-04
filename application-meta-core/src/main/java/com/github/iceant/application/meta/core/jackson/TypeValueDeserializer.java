package com.github.iceant.application.meta.core.jackson;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.iceant.application.meta.core.BasicTypeValue;
import com.github.iceant.application.meta.core.ITypeValue;
import com.github.iceant.application.meta.core.KeyValue;
import com.github.iceant.application.meta.core.utils.ClassUtil;
import com.github.iceant.application.meta.core.values.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class TypeValueDeserializer extends StdDeserializer<ITypeValue> {

    public static final TypeValueDeserializer INSTANCE = new TypeValueDeserializer();

    public TypeValueDeserializer(){
        this(ITypeValue.class);
    }

    protected TypeValueDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ITypeValue deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode node = jsonParser.readValueAsTree();
        if(!node.has("type")){
            if(node.has("value")){
                JsonNode valueNode = node.get("value");
                if(valueNode.isTextual()){
                    return new StringTypeValue().setValue(valueNode.textValue());
                }else if(valueNode.isBigInteger()){
                    return new BigIntegerTypeValue().setValue(valueNode.bigIntegerValue());
                }else if(valueNode.isInt()){
                    return new BigIntegerTypeValue().setValue(BigInteger.valueOf(valueNode.intValue()));
                }else if(valueNode.isBigDecimal()){
                    return new BigDecimalTypeValue().setValue(valueNode.decimalValue());
                }else if(valueNode.isDouble()){
                    return new BigDecimalTypeValue().setValue(BigDecimal.valueOf(valueNode.doubleValue()));
                }else if(valueNode.isBoolean()){
                    return new BooleanTypeValue().setValue(valueNode.booleanValue());
                }else{
                    return new StringTypeValue().setValue(valueNode.textValue());
                }
            }else{
                return null;
            }
        }else{
            String type = node.get("type").textValue();
            return deserializeTypeValue(objectCodec, node, type);
        }

    }

    private ITypeValue deserializeTypeValue(ObjectCodec objectCodec, JsonNode node, String type) throws JsonProcessingException {
        if(!node.has("value")) return null;
        JsonNode valueNode = node.get("value");
        if("BIT".equalsIgnoreCase(type)){
            BitTypeValue typeValue = null;
            if(node.has("offset")){
                typeValue = new BitTypeValue(node.get("offset").intValue());
            }else{
                typeValue = new BitTypeValue(0);
            }
            typeValue.setValue(Byte.valueOf((byte) valueNode.intValue()));
            return typeValue;
        }else if("BYTE".equalsIgnoreCase(type)){
            return objectCodec.treeToValue(node, ByteTypeValue.class);
        }else if("STRING".equalsIgnoreCase(type)){
            return objectCodec.treeToValue(node, StringTypeValue.class);
        }else if("BOOLEAN".equalsIgnoreCase(type)){
            return objectCodec.treeToValue(node, BooleanTypeValue.class);
        }else if("INTEGER".equalsIgnoreCase(type)){
            return objectCodec.treeToValue(node, BigIntegerTypeValue.class);
        }else if("DECIMAL".equalsIgnoreCase(type)){
            return objectCodec.treeToValue(node, BigDecimalTypeValue.class);
        }else if("DATASHAPE".equalsIgnoreCase(type)){
            return objectCodec.treeToValue(node, DataShapeTypeValue.class);
        }else if("ARRAY".equalsIgnoreCase(type)){
            String elementType = node.get("elementTypeName").textValue();
            ArrayTypeValue arrayTypeValue = new ArrayTypeValue(elementType);
            JsonNode arrayNodes = node.get("value");
            List values = new ArrayList<>();

            Class elementTypeClass = null;
            if("KEY-VALUE".equalsIgnoreCase(elementType)){
                elementTypeClass = KeyValue.class;
            }else{
                elementTypeClass = ClassUtil.forName(elementType);
            }
            for(int i=0; i<arrayNodes.size(); i++){
                JsonNode arrayItemNode = arrayNodes.get(i);
                Object value = objectCodec.treeToValue(arrayItemNode, elementTypeClass);
                values.add(value);
            }
            arrayTypeValue.setValue(values.toArray(new Object[values.size()]));
            return arrayTypeValue;
        }else{
            return null;
        }
    }
}

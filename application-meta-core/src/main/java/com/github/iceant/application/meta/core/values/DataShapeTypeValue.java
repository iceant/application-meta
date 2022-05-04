package com.github.iceant.application.meta.core.values;

import com.github.iceant.application.meta.core.BasicTypeValue;
import com.github.iceant.application.meta.core.DataShape;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString(callSuper = true)
public class DataShapeTypeValue extends BasicTypeValue<DataShapeTypeValue, DataShape> {
    final String dataShapeName;

    public DataShapeTypeValue(String dataShapeName) {
        this.dataShapeName = dataShapeName;
    }

    @Override
    public String getType() {
        return "DATASHAPE";
    }
}

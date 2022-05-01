// $engine:
// $config:
// $tableInfo:
// $context

if($tableInfo.entityName=='TAppMenu' || $tableInfo.entityName=='TProtocolAccount'){
    $context.put('fieldMap', {
        "id": "Long"
    });
}

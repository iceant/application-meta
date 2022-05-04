System.register(['./template.html', './style.css'], function(_e, _c){
    let html;
    return {
        setters:[
            function(_){html=_.default;},
            function(_){}
        ],
        execute(){
            _e({
                template:html,
                data(){
                    return {
                        form:{},
                        dataTypes:[],
                        dataShapes:[],
                        selectedDataShape:{}
                    };
                },
                mounted(){
                    let self = this;
                    axios.get('/storage/tDataType/').then(function(res){
                        let result = res.data.result;
                        self.dataTypes = result;
                        console.log(self.dataTypes);
                    }).catch(function(err){
                        console.log(err);
                    });

                    axios.get('/storage/tDataShape/').then(function(res){
                        let result = res.data.result;
                        console.log(res);
                        self.dataShapes = result;
                    });
                },
                methods:{
                    findDataShape(id){
                        for(const k in this.dataShapes){
                            let item = this.dataShapes[k];
                            if(id===item.id){
                                return item;
                            }
                        }
                    },
                    loadDataShapeFields(){
                        let self = this;
                        axios.get('/storage/tDataShape/item/'+self.selectedDataShape.id+'/fields?pageSize=10000').then(function(res){
                            console.log(res);
                            self.selectedDataShape.fields=res.data.result.records;
                        });
                    },
                    handleSelectDataShape(id){
                        let self = this;
                        if(!id){
                            self.selectedDataShape.fields=[];
                            return;
                        }
                        self.selectedDataShape = this.findDataShape(id);
                        self.loadDataShapeFields();
                    }
                }
            });
        }
    }
});
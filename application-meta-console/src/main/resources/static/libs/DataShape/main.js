System.register(['./template.html', './style.css', 'libs/FieldShapeForm'], function(_e, _c){
    let html;
    let FieldShapeForm;
    return {
        setters:[
            function(_){html=_.default;},
            function(_){},
            function(_){FieldShapeForm=_},
        ],
        execute(){
            _e({
                template:html,
                components:{
                    FieldShapeForm: FieldShapeForm
                },
                data(){
                    return {
                        tableData:[],
                        isTabsHidden: true,
                        selectedRow: {},
                        tabsActiveName: 'detail',
                        isDrawerShow: false,
                        form:{}
                    };
                },
                mounted(){
                    let self = this;
                    axios.get('/storage/tDataShape').then(function(res){
                        let d = res.data.result;
                        self.tableData = d;
                    });
                },
                methods:{
                    showFields(row){
                        let self = this;
                        row = row?row:this.selectedRow;
                        axios.get('/storage/tDataShape/item/'+row.id+'/fields').then(function(res){
                            self.selectedRow.selectedFields = res.data.result.records;
                        });
                    },
                    handleBack(){
                        this.$router.go(-1);
                    },
                    handleDetail(row){
                        if(Object.keys(this.selectedRow).length===0 || row===this.selectedRow) {
                            this.isTabsHidden = !this.isTabsHidden;
                        }else{
                            this.isTabsHidden = false;
                        }
                        this.selectedRow = row;
                        this.showFields(row);
                    },
                    handleEdit(){
                        console.log(arguments);
                    },
                    handleTabClick(e){
                        if("fields"===e.props.name){
                            this.showFields(this.selectedRow);
                        }
                    },
                    handleDrawerShow(){
                        this.isDrawerShow=true;
                    },
                    handleAddField(){
                        let self = this;
                        this.form=this.$refs.fieldShapeForm.form;
                        this.form.dataShapeId=this.selectedRow.id;
                        axios.post('/storage/tFieldShape', this.form).then(function(res){
                            let result = res.data.result;
                            self.isDrawerShow=false;
                            self.showFields();
                        }).catch(function (error) {
                            console.log(error);
                        });
                    },
                    handleFieldDelete(id){
                        let self = this;
                        axios.delete('/storage/tFieldShape/'+id).then(function(res){
                            let ret = res.data;
                            console.log(res);
                            self.showFields();
                        }).catch(function(err){
                            console.log(err);
                        });
                    },
                    handleFieldShapeFormCancel(){
                        this.isDrawerShow=false;
                    }
                }
            });
        }
    }
});
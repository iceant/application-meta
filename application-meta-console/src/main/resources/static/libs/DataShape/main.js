System.register(['./template.html', './style.css'], function(_e, _c){
    let html;
    const EMPTY_OBJECT = {};
    return {
        setters:[
            function(_){html=_.default;}
        ],
        execute(){
            _e({
                template:html,
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
                        console.log(arguments);
                        console.log(this.form);
                        let self = this;
                        this.form.dataShapeId=this.selectedRow.id;
                        axios.post('/storage/tFieldShape', this.form).then(function(res){
                            console.log(res);
                            let result = res.data.result;
                            self.isDrawerShow=false;
                            self.showFields();
                        }).catch(function (error) {
                            console.log(error);
                        });;

                    }
                }
            });
        }
    }
});
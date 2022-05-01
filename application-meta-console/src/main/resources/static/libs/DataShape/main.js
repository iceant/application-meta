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
                        selectedFields:[],
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
                        axios.get('/storage/tDataShape/item/'+row.id+'/fields').then(function(res){
                            self.selectedFields = res.data.result;
                        });
                    },
                    handleBack(){
                        this.$router.go(-1);
                    },
                    handleDetail(row){
                        let tabs = this.$el.querySelector('.data-shape-tabs');
                        if(Object.keys(this.selectedRow).length===0 || row===this.selectedRow) {
                            this.isTabsHidden = !this.isTabsHidden;
                        }else{
                            this.isTabsHidden = false;
                        }
                        this.selectedRow = row;
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
                    }
                }
            });
        }
    }
});
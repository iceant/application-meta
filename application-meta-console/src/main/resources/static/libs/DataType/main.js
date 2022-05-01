System.register(['./template.html', './style.css'], function(_e, _c){
    let html;

    return {
        setters:[
            function(_){html=_.default;}
        ],
        execute(){
            _e({
                template:html,
                data(){
                    return{
                        tableData:[]
                    }
                },
                mounted(){
                    let self = this;
                    axios.get('/storage/tDataType/').then(function(res){
                        let d = res.data.result;
                        self.tableData=d;
                    });
                },
                methods:{
                    handleBack(){
                        this.$router.go(-1);
                    }
                }
            });
        }
    }
});
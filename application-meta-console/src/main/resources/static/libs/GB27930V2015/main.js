System.register(['./template.html'], function(_e, _c){
    let html;

    return {
        setters:[
            function(_){html=_.default;}
        ],
        execute(){
            _e({
                template:html
                ,methods:{
                    handleBack(){
                        this.$router.go(-1);
                    }
                }
            });
        }
    }
});
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
                    return {
                        isCollapse:true
                    }
                },
                methods:{
                    handleOpen(key, keyPath){
                        console.log(key, keyPath);
                    },
                    handleClose(key, keyPath){
                        console.log(key, keyPath);
                    }
                }
            });
        }
    }
});
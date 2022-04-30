System.register(['./template.html', './style.css', 'libs/menu'], function(_e, _c){
    let html;
    let css ;
    let Menu;
    return {
        setters:[
            function(_){html=_.default;},
            function(_){css=_.default;},
            function(_){Menu=_;}
        ],
        execute(){
            _e({
                template:html,
                data(){
                    return {
                        isCollapse:true
                    }
                },
                components:{
                    'meta-menu': Menu
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
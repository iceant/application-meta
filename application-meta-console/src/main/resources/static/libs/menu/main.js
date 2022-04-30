System.register(['./template.html', 'libs/NotFound'], function(_e, _c){
    let html;
    let NotFound;
    return {
        setters:[
            function(_){html=_.default;},
            function(_){NotFound=_;}
        ],
        execute(){
            _e({
                template:html,
                data(){
                    return {
                        menus:[]
                    }
                },
                mounted(){
                    let self = this;
                    axios.get('/storage/tAppMenu/firstLevel').then(function(res){
                        self.menus=res.data.result;
                    });
                },
                methods:{
                    registerRoute(menus){
                        for(let i=0; i<menus.length; i++){
                            let menu = menus[i];
                            this.$router.addRoute({path: menu.path, component: menu.view?menu.view:NotFound});
                        }
                    },
                    setSubMenus(id, menus){
                        for(let i=0; i<this.menus.length; i++){
                            let menu = this.menus[i];
                            if(menu.id===id){
                                menu.menus=menus;
                                break;
                            }
                        }
                    },
                    findMenu(id, menus){
                        for(let i=0;i<menus.length; i++){
                            let menu = menus[i];
                            if(menu.id===id){
                                return menu;
                            }
                            if(menu.menus){
                                let item = this.findMenu(id, menu.menus);
                                if(item){
                                    return item;
                                }
                            }
                        }
                    },
                    handleOpen(key, keyPath){
                        let self = this;
                        axios.get('/storage/tAppMenu/'+key+'/subMenus').then(function(res){
                            let menus = res.data.result;
                            self.setSubMenus(key, menus);
                        });
                    },
                    handleClick(key, keyPath){
                        let self = this;
                        let menu = this.findMenu(key.index, this.menus);
                        console.log(key.index, 'libs/'+menu.view);
                        if(menu && menu.view){

                            _c.import('libs/'+menu.view).then(function(m){
                                self.$router.addRoute({path: menu.path, component: m});
                                self.$router.push({path: menu.path});
                            });
                        }
                    }
                }
            })
        }
    }
});
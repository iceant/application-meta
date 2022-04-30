System.register(['libs/layout', './style.css'], function(_e, _c){
    let Layout;
    return {
        setters:[
            function(_){Layout=_;}
        ],
        execute(){
            const app = Vue.createApp(Layout);
            app.use(ElementPlus);
            for(const k in ElementPlusIconsVue){
                app.component(k, ElementPlusIconsVue[k]);
            }
            app.mount('#app');
        }
    }
});
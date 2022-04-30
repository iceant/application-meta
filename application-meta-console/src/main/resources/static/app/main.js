System.register(['libs/layout', './style.css', 'libs/NotFound'], function(_e, _c){
    axios.defaults.baseURL='http://localhost:8080';

    let Layout;
    let NotFound;
    return {
        setters:[
            function(_){Layout=_;},
            function(_){},
            function(_){NotFound=_;}
        ],
        execute(){
            const app = Vue.createApp(Layout);
            // ElementPlus
            app.use(ElementPlus);
            app.component('NotFound', NotFound);

            // Icons
            for(const k in ElementPlusIconsVue){
                app.component(k, ElementPlusIconsVue[k]);
            }

            // Router
            const routes=[
                { path: '/:pathMatch(.*)*', name: 'NotFound', component: NotFound },
            ];
            const router = VueRouter.createRouter({
                history: VueRouter.createWebHashHistory(),
                routes: routes,
            });
            app.use(router);

            app.mount('#app');
        }
    }
});
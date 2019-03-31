/**
 * 第一个参数是组件的名称
 * 第二个参数是组件的配置对象
 * 该配置对象和new Vue的配置对象一样（有data，计算属性，过滤器属性等）
 * 除el属性之外的所有属性
  */

Vue.component("addr-casecade",{
    props:{
        test:{
            type:String,
            default:'地址'
        },
        v1:{
            type:Number,
            default:11
        },
        v2:{
            type:Number,
            default:1101
        },
        v3:{
            type:Number,
            default:110101
        },
        level:{
            type:Number,
            default:3
        }
    },
    // el:'#app',组件没有el属性
    //data属性（在组件中data属性必须定义为function返回对象的方法
    data:function(){
        return{
        list1: pca,
        v1: 'v1',
        v2: 'v2',
        v3: 'v3'}
    },
    // 计算属性
    computed:{
        list2: function () {
            return this.getList(this.list1,this.v1);
        },
        list3:function () {
            return this.getList(this.list2,this.v2);
        }
    },

    // 方法属性
    methods:{
        getList: function(list,code) {
            for(el of list){
                if(el.code == code){
                    return el.children;
                }
            }
            return [];
        }
    },
    // 过滤器属性
    filters:{

    },
    //template模板属性，用于定义组件的html模板的
    //该属性用的引号建议使用反引号（不支持的浏览器除外）
    //该模板必须有且仅有一个根节点（如div）
    template:`<div>
<label>{{test}}</label>
    <select v-model="v1">
        <option value="" disabled>选择省</option>
        <option v-for="prov of list1" :value="prov.code">{{prov.name}}</option>
    </select>
    <select v-model="v2" v-if="level >=2">
        <option value="" disabled >选择市</option>
        <option v-for="prov of list2" :value="prov.code">{{prov.name}}</option>
    </select>
    <select v-model="v3" v-if="level >=3">
        <option value="" disabled>选择市</option>
        <option v-for="prov of list3" :value="prov.code">{{prov.name}}</option>
    </select>
</div>`
});
webpackJsonp([7],{GF4k:function(e,r,s){"use strict";Object.defineProperty(r,"__esModule",{value:!0});var t=s("JBQq"),a={data:function(){return{ruleForm:{username:"admin",password:"123456"},rules:{username:[{required:!0,message:"请输入用户名",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"}]}}},created:function(){this.$route.query.message&&this.$message({showClose:!0,message:this.$route.query.message,type:"warning"})},methods:{submitForm:function(e){var r=this;this.$refs[e].validate(function(e){if(!e)return r.$message({showClose:!0,message:"输入有误，请重新输入",type:"error"}),!1;var s={username:r.ruleForm.username,password:r.ruleForm.password};t.a.post("login",s).then(function(e){200===e.httpCode?(localStorage.setItem("username",r.ruleForm.username),localStorage.setItem("realname",e.data.realname),localStorage.setItem("password",r.ruleForm.password),r.$route.query.path?r.$router.push(r.$route.query.path):r.$router.push("/")):r.$message({showClose:!0,message:e.message,type:"error"})})})}}},o={render:function(){var e=this,r=e.$createElement,s=e._self._c||r;return s("div",{staticClass:"login-wrap"},[s("div",{staticClass:"ms-title"},[e._v("Code Generator")]),e._v(" "),s("div",{staticClass:"ms-login"},[s("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{model:e.ruleForm,rules:e.rules,"label-width":"0px"}},[s("el-form-item",{attrs:{prop:"username"}},[s("el-input",{attrs:{placeholder:"请输入用户名"},model:{value:e.ruleForm.username,callback:function(r){e.$set(e.ruleForm,"username",r)},expression:"ruleForm.username"}})],1),e._v(" "),s("el-form-item",{attrs:{prop:"password"}},[s("el-input",{attrs:{type:"password",placeholder:"请输入密码"},nativeOn:{keyup:function(r){if(!("button"in r)&&e._k(r.keyCode,"enter",13,r.key,"Enter"))return null;e.submitForm("ruleForm")}},model:{value:e.ruleForm.password,callback:function(r){e.$set(e.ruleForm,"password",r)},expression:"ruleForm.password"}})],1),e._v(" "),s("div",{staticClass:"login-btn"},[s("el-button",{attrs:{type:"primary"},on:{click:function(r){e.submitForm("ruleForm")}}},[e._v("登录")])],1)],1)],1)])},staticRenderFns:[]};var u=s("VU/8")(a,o,!1,function(e){s("U62l")},"data-v-be12db6c",null);r.default=u.exports},U62l:function(e,r){}});
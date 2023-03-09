
// ресурс, отвечающий за связь с сервером для сообщений
var messageApi = Vue.resource('rest/message{/userMessage}{/chatId}{/userId}');
// ресурс, отвечающий за связь с сервером для пользователей
var userApi = Vue.resource('rest/user{/userId}');
// ресурс, отвечающий за связь с сервером для чатов
var chatApi = Vue.resource('rest/chat{/chatName}');

// регистрация пользователя
Vue.component('registration-form',{
  data: function(){
    return{
      userName:''
    }
  },
  template: 
  '<div class="container py-5 h-100">'+
  '<div class="row d-flex justify-content-center align-items-center h-100">'+
    '<div class="col-12 col-md-8 col-lg-6 col-xl-5">'+
      '<div class="card bg-white text-dark" style="border-radius: 1rem;">'+
        '<div class="card-body p-5 text-center">'+

          '<div class="mb-md-5 mt-md-4 pb-5">'+

            '<h2 class="fw-bold mb-2 text-uppercase">Вход</h2>'+
            '<p class="text-dark-50 mb-5">Пожалуйста, введите имя пользователя!</p>'+

            '<div class="form-outline form-dark mb-4">'+
              '<input v-model ="userName" type="email" id="typeEmailX" class="form-control form-control-lg" />'+ 
            '</div>'+

            '<button @click ="sendUser" class="btn btn-outline-dark btn-lg px-5" type="submit">Войти</button>'+

          '</div></div></div></div></div></div>',
  
  methods:{
    sendUser(){
      var user = {userName: this.userName};

      userApi.save({}, user).then(result =>
        result.json().then(data =>{
          this.$emit('updateUserData', data)
          this.userName = '';
        }))
    }
  }
 });


//отправка сообщений
Vue.component('message-form',{
  props:['messages','userId','chatId'],
  data: function(){
    return{
      messageText:''
    }
  },
  template: '<div class="d-flex p-2 bd-highlight fixed-bottom">'+
            '<input type="text" '+
            'class="form-control form-control-lg"'+
                    'v-model ="messageText"'+
                    'placeholder="Write a message..."/>'+
            '<input type="button" '+
            'class="btn btn-outline-dark btn-lg px-5 col-1 "'+
                    'value="Send"'+
                    '@click ="sendMessage"/>'+
            '</div>',
  methods:{
    sendMessage(){
      messageApi.get({userMessage: this.messageText, chatId: this.chatId, userId: this.userId}).then(result =>
        result.json().then(data => {
          console.log('ошибка есть?')
          this.messageText = '';
          this.messages.push(data.messageText);
        })
      )
  }
}
            
});


//одно сообщение
Vue.component('message-item',{
  props: ['message'],
  template: '<div>{{message}}</div>'
 });
  
//список сообщений
  Vue.component('messages-list',{
    props: ['messages','userId','chatId'],
   template:
   '<div>'+
            '<message-item class="list-group-item" v-for="message in messages" :message ="message"/>'+
                '<message-form :messages="messages" :userId="userId" :chatId="chatId"/>' +
  '</div>'
   });
// один чат
    Vue.component('chat-item',{
    props:['chatName'],
    template: '<div>'+
                    '<button type="button" @click="enterChat" class="list-group-item list-group-item-action" >{{chatName}}</button>' +
              '</div>',
    methods:{
      enterChat(){
        chatApi.get({chatName: this.chatName}).then(result =>
          result.json().then(data =>{
            this.$emit('viewMessages', data);
          }));
        chatApi.save({chatName: this.chatName}, {}).then(result =>
          result.json().then(data =>{
            this.$emit('updateChatId', data);
          }))
      }
    }
  });
// список чатов
    Vue.component('chat-list',{
    props:['userId'],
    template: '<div>'+
                    '<div>{{userId}}</div>'+
                    '<chat-item v-for="chat in chats" :chatName="chat" @viewMessages ="viewMessages" @updateChatId="updateChatId"/>'+
              '</div>',
    data: function(){
      return{
        chats:[]
      }
    },
    methods:{
      viewMessages(messages){
        this.$emit('updateMessages', messages)
      },
      updateChatId(chatId){
        this.$emit('updateChatId', chatId)
      }
    },
    created: function(){
      userApi.get({userId: this.userId}).then(result =>
        result.json().then(data =>
          data.forEach(chat => this.chats.push(chat))
          ))
    },

   });


   var app = new Vue({
     el: '#app',
     template:  '<div v-if="user.userId == 0"><registration-form @updateUserData="updateUserData" /></div>'+
                '<div v-else class="d-flex p-2 bd-highlight">'+
                '<chat-list :userId="user.userId" @updateMessages ="updateMessages" @updateChatId ="updateChatId" class="col-3"/>'+
                '<messages-list :messages="messages" :userId="user.userId" :chatId="chatId" class="list-group col-5"/>'+
                '</div>'
                ,
     data: {
       messages: [],
       user: {userId: 0, userName:''},
       chatId: 0
     },
     methods:{
      updateUserData(user){
        this.user = user;
      },
      updateMessages(messages){
        this.messages = [];
        messages.forEach(message => this.messages.push(message))
      },
      updateChatId(chatId){
        this.chatId = chatId
      }
    }
   });
'use strict';

const functions = require('firebase-functions');
const {WebhookClient} = require('dialogflow-fulfillment');
const {Card, Suggestion} = require('dialogflow-fulfillment');
const admin = require('firebase-admin');
admin.initializeApp();
const db = admin.firestore();

process.env.DEBUG = 'dialogflow:debug'; // enables lib debugging statements

exports.dialogflowFirebaseFulfillment = functions.https.onRequest((request, response) => {
  const agent = new WebhookClient({ request, response });

  //INSERT DATA
  function name_function_insert(agent){
    let name_variable = agent.parameters.NAME_VARIABLE_IN_AGENT;
    
    db.collection("name_db").add({
      name_variable_in_db: name_variable
    });
    
    agent.add("RESPONSE FULFILLMENT");
  }
  
  //SHOW DATA
  function name_function_show(agent){
    return db.collection("name_db").get().then(function(documents){
      
      if(documents === 0){
        agent.add("NO EXIST DOCUMENTS");
      }else{
       	let response = "YOUR_MESSAGE";
        
        documents.forEach(function(document){
         
          const dataOutput = document.data();
          response += "\n -> "+dataOutput.name_variable_in_db+" 👍";
        
        });//END forEach
        agent.add(response);
      }//END else
      
    }).catch(() => {
      
      agent.add("SERVER ERROR");
      
    });//END catch
  }
  
  //SHOW DATA WHERE
  function name_function_show_where(agent){
    return db.collection("name_db").where("your_field", "==","value")
      .get().then(function(documents){
      
      if(documents === 0){
        agent.add("NO EXIST DOCUMENTS");
      }else{
       	let response = "YOUR_MESSAGE_WHERE";
        
        documents.forEach(function(document){
         
          const dataOutput = document.data();
          response += "\n -> "+dataOutput.name_variable_in_db+" "+ 
            dataOutput.name_variable_in_db+" 👍";
        
        });//END forEach
        agent.add(response);
      }//END else
      
    }).catch(() => {
      
      agent.add("SERVER ERROR");
      
    });//END catch
  }
  

  let intentMap = new Map();
  intentMap.set('NAME_INTENT_INSERT', name_function_insert);
  intentMap.set('NAME_INTENT_SHOW', name_function_show);
  intentMap.set('NAME_INTENT_SHOW_WHERE', name_function_show_where);
  agent.handleRequest(intentMap);
});

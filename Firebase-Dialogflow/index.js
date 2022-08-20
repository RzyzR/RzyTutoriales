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

  function name_function(agent){
    let name_variable = agent.parameters.NAME_VARIABLE_IN_AGENT;
    
    db.collection("name_db").add({
      name_variable_in_db: name_variable
    });
    
    agent.add("Respuesta desde el Fulfillment");
  }

  let intentMap = new Map();
  intentMap.set('name_intent', name_function);
  agent.handleRequest(intentMap);
});

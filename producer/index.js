const { Kafka } = require('kafkajs');

const kafka = new Kafka({
    clientId: 'my-producer',
    brokers: ['localhost:9092']
});

const producer = kafka.producer();

const produceMessage = async () => {
    await producer.connect();
    await producer.send({
        topic: 'my-topic',
        messages: [
            {value: 'Hello from Kafka producer'}
        ],
    });
    console.log('Message sent!');
    await producer.disconnect();
};

produceMessage().catch(console.error)
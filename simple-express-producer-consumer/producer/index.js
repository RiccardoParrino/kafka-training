const { Kafka } = require('kafkajs');

const kafka = new Kafka({
    clientId: 'my-producer',
    brokers: ['localhost:9092']
});

const producer = kafka.producer();

const produceMessageTopic1 = async () => {
    await producer.connect();
    await producer.send({
        topic: 'topic-1',
        messages: [
            {value: 'Hello from topic 1'}
        ],
    });
    console.log('Message sent!');
    await producer.disconnect();
};

const produceMessageTopic2 = async() => {
    await producer.connect();
    await producer.send({
        topic: 'topic-2',
        messages: [
            {value: 'Hello from topic 2'}
        ]
    })
}

produceMessageTopic2().catch(console.error)
produceMessageTopic1().catch(console.error)
const {Kafka} = require('kafkajs')

const kafka = new Kafka({
    clientId: 'my-consumer',
    brokers: ['localhost:9092'],
});

const consumer = kafka.consumer({groupId:'my-group'})

const consumeMessagesTopic1 = async () => {
    await consumer.connect();
    await consumer.subscribe({
        topic: 'topic-1',
        fromBeginning: true
    });

    await consumer.run({
        eachMessage: async ({topic, partition, message}) => {
            console.log({
                value: message.value.toString(),
            })
        }
    })
}

const consumeMessagesTopic2 = async () => {
    await consumer.connect()
    await consumer.subscribe({
        topic: 'topic-2',
        fromBeginning: true
    })
    await consumer.run({
        eachMessage: async ({topic, partition, message}) => {
            console.log(
                topic + " " +
                partition + " " + 
                message.value.toString()
            )
        }
    })
}

consumeMessagesTopic1().catch(console.error)
consumeMessagesTopic2().catch(console.error)
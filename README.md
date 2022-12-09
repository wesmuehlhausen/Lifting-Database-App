# Lifting-Database-App
### Description
The **purpose** of this app is to keep track of your workouts, sleep, and diet. There are a lot of professional apps already out there that do parts of this really well, but the reason I created this is that most of them you need to pay for, and I wanted something more specific. Certain apps will flood you a lot of workout analysis that isn't important. This app focuses on providing focused and relevant data tracking in the following three aspects of fitness:</b>

- **Workouts** is focused specifically for powerlifting and bodybuilding i.e. "power-building". Workouts will be focused on either strength (powerlifting) or hypertrophy/mass (bodybuilding). Users will be able to log their workout into a database, view analytics, and generate new workouts with specific weights based on prior workouts.
- **Diet** is something that I have found to be __nearly impossible__ to work effectively without proper logging and planning. Lifters can be either cutting or bulking depending on thier goals, and in my case it is bulking. In most cases, tracking macros (protein, carbs, and fat) is a good time/effort vs. outcome way to keep track of your diet. This portion of the app will keep track and see if you are hitting all of your goals as well as keep a database of all your foods to make it easier to input your food data. 
- **Sleep** is subtle but very important. This keeps track of your sleep. Simple as that! I might throw in a sleep API using a fitbit. Not sure yet.

### Model 1 of this app is a simple program that consists of the following:
#### Back-end: Java 
- Java backend using Maven configuration which uses various dependencies. The backend performs various data analytics, database queries, and user account configuration.
- This app is **hardened** to prevent SQL Injection, and keeps passwords stored safely as MD5 Hashes which have been salted using SHA1PRNG algorithm.
#### Front-end: Swing
- Developed in Swing using the IntelliJ GUI builder that I just realized was a thing. This will eventually evolve into some web-based front end.
#### Database: PostgreSQL, MongoDB ATlas, MongoDB Compass
- This app uses a PostgreSQL server run locally on my machine. It also has been configured to run on a cloud database from MongoDB Atlas depending on user settings. I also built it so it can use a local database MongoDB Compass. But if I end up putting this on my website for people to download and use, the initial version will probably use a local database in the application to make it simpler for the user. 

### Future Plans
Right now I am learning the Web: Ruby, HTML/CSS, Javascript, Databases, Ruby on Rails. So my long term plan is to turn this into a web app. 

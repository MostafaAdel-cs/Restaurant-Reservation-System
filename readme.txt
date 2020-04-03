Restaurant reservation system


won't work unless adding this part to the xml file provided in the pdf
 <orders>
        <order>
            <dishes>
            </dishes>
            <customerUserName></customerUserName>
            <tableNumber>0</tableNumber>
            <served>false</served>
            <cooked>false</cooked>
            <number>0</number>
        </order>
    </orders>
this part must be added after the tables list section meaning between 
</tables>   and </restaurant>



this application has it's logic class which is independent from the gui.
And is able to work with different gui without changing the methods.

The code is mainly in logic class which is divided in to 5 sections..
First section is for add user and some setting up for the program .
Second sections contains logic for ccustomer..
Third section for cook..
Fourth section for waiter..
Fifth section for manger..
How the program works??
First the user enters password and username and then directed to his dashboard..
If the user is customer he could make an order but first have to set his table then he could make his order after making his order he could edit it or change table as he likes until his order is seen by the cook and set as cooked.. then the order will appear to the waiter as ready to get served the waiter sees only the table where he should serve the food we didn’t add unnecessary information to any dash board..
While the manger can see the order weather it is served or cooked or not and he can add different kinds of dishes and tables and sees the total gain in this day the total gain is edited every time the manger enters the orders that are paid he can’t add any order as paid unless it is served and cooked.. the manger also can see list of users and customer names and roles we gave the manger complete access to the restaurant assuming he is the owner.. the program is designed to run for one complete day as requested by Technical Assistants..  

Users are instructed not to input any kind of wrong data such as the when the orders appear in the dashboard of the cook he should enter the numbers appearing only else it could cause some unexpected errors..


The project was divided one of us did most of gui and the other one focused on logic class..










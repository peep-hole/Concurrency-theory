
function printAsync(s, cb, n) {
    var delay = Math.floor((Math.random()*1000)+500);
    setTimeout(function() {
        console.log(s);
        if (cb) cb(n);
    }, delay);
}

function task1(cb, n) {
    printAsync("1", function() {
        task2(cb, n);
    });
}

function task2(cb, n) {
    printAsync("2", function() {
        task3(cb, n);
    });
}

function task3(cb, n) {
    printAsync("3", cb, n);
}

// wywolanie sekwencji zadan
// task1(function() {
//     console.log('done!');
// });


/* 
** Zadanie:
** Napisz funkcje loop(n), ktora powoduje wykonanie powyzszej 
** sekwencji zadan n razy. Czyli: 1 2 3 1 2 3 1 2 3 ... done
** 
*/

function loop(n) {
    if (n === 0) {
        task1(function () {
            console.log("done!")
        })
    }
    else {

        task1(loop, n-1)
    }
}

loop(4);


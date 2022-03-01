export function onTimerChange(state,time){
    console.log(time)
    return state;
}

export function onQuizEnd(state){
    console.log(state)
}

// exemple de function pour gérer les questions avec la navigation
export function onAnswerClick(state,item, index,i){

    if(item == state.questions[i].rightAnswer ){
        if(state.indexQuestion < state.questions.length-1){
            state.score = state.score +1;
            state.indexQuestion = state.indexQuestion +1;
        }

    }
    else{
        state.questions[state.indexQuestion].answers[index] = " X "
    }
    return state;
}

// exemple de function pour gérer toutes les questions sur la page
/**export function onAnswerClick(state,item, index,i){

    if(item == state.questions[i].rightAnswer ){
        if(state.indexQuestion < state.questions.length-1){
            state.score = state.score +1;
        }

    }
    else{
        state.questions[state.indexQuestion].answers[index] = " X "
    }
    return state;
}*/

export function onMultipleAnswerChange(state ,value, option,setState,i){
    console.log(state)
    return state;
}
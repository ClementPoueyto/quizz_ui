export function onTimerChange(state,time){
    console.log(time)
    return state;
}

export function onAnswerClick(state,item, index){

    if(item == state.questions[state.indexQuestion].rightAnswer ){
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

export function onMultipleAnswerChange(state ,value, option){
    return state;
}
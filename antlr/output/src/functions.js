export function onTimerChange(state,time){
    console.log(time)
    return state;
}

export function onAnswerClick(state,item, index){
    state.questions[state.indexQuestion].answers[index] = " jai clique dessus"
    if(item == state.questions[state.indexQuestion].rightAnswer){
        state.score = state.score +1;
        state.indexQuestion = state.indexQuestion +1;
    }
    return state;
}

export function onMultipleAnswerChange(state ,value, option){
    return state;
}
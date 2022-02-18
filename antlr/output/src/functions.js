export function onTimerChange(state,time){
    console.log(time)
}

export function onAnswerClick(state,item, index){
    console.log(state)
    state.answers[index] = " jai clique dessus"
    console.log(item)
    console.log(index)
    return state;
}
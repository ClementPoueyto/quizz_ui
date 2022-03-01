import React, { Component } from 'react'
import {  onAnswerClick,  onTimerChange, onMultipleAnswerChange, onQuizEnd} from './functions'
import { Grommet, Grid, Box, CheckBoxGroup, Text, Button, Clock, Image, ResponsiveContext, TextInput, Meter } from 'grommet'
import { deepMerge } from "grommet/utils";
import { grommet } from "grommet/themes";
var data = require('./quiz.json');

export default class App extends Component {

	constructor() {
		super();
		this.state = { quiz : data,
		indexQuestion : 0}
	}

nextQuestion(){
	if(this.state.indexQuestion !== this.state.quiz.questions.length-1){
		this.setState(prevState => {
			return {indexQuestion: prevState.indexQuestion + 1}
		})
	}
}
previousQuestion(){
	if(this.state.indexQuestion !== 0){ 
		this.setState(prevState => {
			return {indexQuestion: prevState.indexQuestion - 1}
		})
	}
}
	renderQuestion(){
		let i = this.state.indexQuestion;
				return(<>
					{this.state.quiz.questions[i].statement.text !== undefined &&<Text size='medium'  textAlign='center'  color='light-2'  >{this.state.quiz.questions[i].statement.text}</Text>
}
{this.state.quiz.questions[i].statement.image !== undefined &&  <Box height="auto" width="auto">
<Image src= {this.state.quiz.questions[i].statement.image} /></Box>}					{this.state.quiz.questions[i].answers===undefined &&<TextInput size="medium" textAlign="center"/>
}{this.state.quiz.questions[i].rightAnswer?.length===1 && this.state.quiz.questions[i].answers.map((item,index)=>{
							return <Button primary={true}  size='small'  margin='small'  color='light-2'  alignSelf='center'  onClick={()=>{this.setState(onAnswerClick(this.state.quiz,item,index,i))}}  label={this.state.quiz.questions[i].answers[index]}  />
					})}
{this.state.quiz.questions[i].rightAnswer?.length>1 && <CheckBoxGroup options = { this.state.quiz.questions[i].answers } onChange={ ({ value, option }) => { this.setState(onMultipleAnswerChange(this.state.quiz,value,option,i))}} gap = 'small'  />}
					</>)
	}
	render() {
		var customBreakpoints = deepMerge(grommet, {
			global: {
				breakpoints: {
					small: {
						 value: 600
					},
					medium: {
						value: 950
					},
					large: 3000
				},
			}
		});
		var c_areas= []
		const areas = {
			default: [
				["left","header",],
				["leftbottom","middle",],
				["nav","nav",],
			],
			medium: [
				["left","header",],
				["left","middle",],
				["nav","nav",],
			],
			small: [
				["header",],
				["middle",],
				["nav",],
			],
		}
		const rows = {
			default:['small','medium','xsmall',],
			medium:['medium','medium','xsmall',],
			small:['small','medium','xsmall',],
		}
		const columns = {
			default:['medium','auto',],
			medium:['medium','auto',],
			small:['auto',],
}
		const gaps = {
			default:'null',
			medium:'null',
			small:'null',
}
		return (
			<Grommet theme={customBreakpoints}>
				<ResponsiveContext.Consumer>
					{size =>
					<Grid
						rows={rows[size] ? rows[size] : rows["default"]}
						columns={columns[size] ? columns[size] : columns["default"]}
						gap={gaps[size] ? gaps[size] : gaps["default"]}
						areas={areas[size] ? areas[size] : areas["default"]}>
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("header") >=0) ?
						<Box overflow='auto' gridArea='header' align='center'   background='dark-2' >
						<Text size='xlarge'  textAlign='center'  color='light-2'  >{this.state.quiz.title}</Text>
						<Text size='large'  textAlign='center'  color='light-2'  >{this.state.quiz.theme}</Text>
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("middle") >=0) ?
						<Box overflow='auto' gridArea='middle' align='center'   background='dark-2' >
							{this.renderQuestion()}
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("nav") >=0) ?
						<Box overflow='auto' gridArea='nav' align='center'   background='dark-2' >
						<Box  background="unset" flex={true} fill={true} direction="row" basis="auto" >
						{this.state.indexQuestion!==0 && 
							<Button primary={true}  size='small'  margin='auto'  color='light-2'  alignSelf='end'  onClick={()=>{this.previousQuestion()}}  label={'previous'}  />
						}
						{this.state.indexQuestion!==this.state.quiz.questions.length-1?
								<Button primary={true}  size='small'  margin='auto'  color='light-2'  alignSelf='start'  onClick={()=>{this.nextQuestion()}}  label={'next'}  />
							:
								<Button primary={true}  size='small'  margin='auto'  color='light-2'  alignSelf='start'  onClick={()=>{onQuizEnd(this.state)}}  label={'next'}  />
							}
						</Box>
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("left") >=0) ?
						<Box overflow='auto' gridArea='left' align='center'   background='dark-2' >
						<Meter size='small'  type='circle'  color='light-2'  thickness='medium' value = {this.state.indexQuestion*100/this.state.quiz.questions.length}  />
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("leftbottom") >=0) ?
						<Box overflow='auto' gridArea='leftbottom' align='center'   background='dark-2' >
						<Clock run='forward'  type='analog'  size='large'  time='T00:00:00'  alignSelf='center'  precision='seconds'  onChange={(time)=>{ this.setState({ quiz : onTimerChange(this.state.quiz,time)})}}  />
						</Box>
						:
						<Box/>
					}
				</Grid>
			}
			</ResponsiveContext.Consumer>
		</Grommet>
		);
	}
}
